export interface CreateEventForm {
  bidderId: number;
  name: string;
  description: string;
  image: string;
  announcementDate: string;
  dates: Array<Dates>;
  localization: Localization;
  insignia: Insignia;
  score: number;
  exigency: number;
}

export interface Dates {
  startDate: string;
  finalDate: string;
  price: number;
  places: number;
  placesAvailable: number;
}

export interface Localization {
  latitude: string;
  longitude: string;
  municipality: string;
  province: string;
  country: string;
}

export interface Insignia {
  name: string;
  image: string;
  value: number;
}
