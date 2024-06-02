import { Component, OnInit, inject } from '@angular/core';
import { Bidder } from '../../../models/Bidder';
import { DataService } from '../../../services/data.service';
import { CreateEventForm, Dates } from '../../../models/CreateEventForm';
import { FormsModule } from '@angular/forms';
import {
  GoogleMapsModule,
  MapAdvancedMarker,
  MapGeocoder,
  MapInfoWindow,
  MapMarker,
} from '@angular/google-maps';
import { EventService } from '../../../services/event.service';
import { environment } from '../../../../environments/environment.development';
import { Router } from '@angular/router';

@Component({
  selector: 'app-create-event',
  standalone: true,
  imports: [
    FormsModule,
    GoogleMapsModule,
    MapMarker,
    MapAdvancedMarker,
    MapInfoWindow,
  ],
  templateUrl: './create-event.component.html',
  styleUrl: './create-event.component.css',
})
export class CreateEventComponent implements OnInit {
  public bidder: Bidder = <Bidder>{};
  public createEventForm: CreateEventForm = <CreateEventForm>{};
  public date: Dates = {} as Dates;

  //
  title = 'google-maps-angular';
  center: google.maps.LatLngLiteral = { lat: 24, lng: 12 };
  zoom = 4;
  markerPosition: google.maps.LatLngLiteral | undefined;
  locationData: any = {};
  //

  private geoCoder: MapGeocoder = inject(MapGeocoder);
  private dataService: DataService = inject(DataService);
  private eventService: EventService = inject(EventService);
  private ruta: Router = inject(Router);

  ngOnInit(): void {
    this.dataService.ofertante$.subscribe((data) => {
      this.bidder = data;
    });
  }

  onSubmit(createForm: any) {
    const response: CreateEventForm = {
      bidderId: this.bidder.id,
      name: createForm.name,
      description: createForm.description,
      image: environment.DEFAULT_PROFILE_IMAGE,
      announcementDate: new Date().toISOString(),
      dates: [
        {
          startDate: this.date.startDate,
          finalDate: this.date.finalDate,
          price: this.date.price,
          places: this.date.places,
          placesAvailable: this.date.places,
        },
      ],
      localization: {
        latitude: createForm.latitude.toString(),
        longitude: createForm.longitude.toString(),
        municipality: createForm.municipality,
        province: createForm.province,
        country: createForm.country,
      },
      insignia: {
        name: createForm.name,
        image: environment.DEFAULT_PROFILE_IMAGE,
        value: 3,
      },
      score: createForm.score,
      exigency: createForm.exigency,
    };

    this.eventService.addEvent(response).subscribe({
      next: (data) => {
        this.ruta.navigate(['/home']);
      },
      error: (err) => {
      },
    });
  }

  mapClick(event: google.maps.MapMouseEvent) {
    if (event.latLng) {
      this.markerPosition = event.latLng.toJSON();
      this.getGeocodingData(this.markerPosition.lat, this.markerPosition.lng);
    }
  }

  getGeocodingData(lat: number, lng: number) {
    const geocoder = new google.maps.Geocoder();
    const latlng = { lat, lng };

    geocoder.geocode({ location: latlng }, (results, status) => {
      if (results) {
        if (status === 'OK' && results[0]) {
          this.locationData = this.extractLocationData(results[0]);
          this.createEventForm.localization = {
            ...this.createEventForm.localization,
            latitude: this.locationData.latitude,
            longitude: this.locationData.longitude,
            country: this.locationData.country,
            province: this.locationData.municipality,
            municipality: this.locationData.city,
          };
        } else {
        }
      }
    });
  }

  extractLocationData(result: google.maps.GeocoderResult) {
    const locationData: any = {
      latitude: this.markerPosition?.lat,
      longitude: this.markerPosition?.lng,
    };

    result.address_components.forEach((component) => {
      if (component.types.includes('locality')) {
        locationData.city = component.long_name;
      }
      if (component.types.includes('administrative_area_level_2')) {
        locationData.municipality = component.long_name;
      }
      if (component.types.includes('administrative_area_level_1')) {
        locationData.province = component.long_name;
      }
      if (component.types.includes('country')) {
        locationData.country = component.long_name;
      }
    });

    return locationData;
  }
}
