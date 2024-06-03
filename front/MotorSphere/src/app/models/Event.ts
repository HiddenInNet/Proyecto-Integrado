import { FechaEvento } from './FechaEvento';
import { Localization } from './Localization';

export interface Event {
  eventId: number;
  bidderId: number;
  insigniaId: number;
  name: string;
  description: string;
  image: string;
  announcementDate: string;
  dates: Array<FechaEvento>;
  localization: Localization;
  score: number;
  exigency: number;
}

export interface AddUserToEvent {
  userId: number;
  eventId: number;
  incriptionDate: string | null;
  fechaId: number;
}
