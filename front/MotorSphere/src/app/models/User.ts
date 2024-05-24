import { Publication } from './Publication';

export interface User {
  id: number;
  email: string;
  name: string;
  username: string;
  lastName: string;
  birthDate: string;
  phone: string;
  profileDate: string;
  biography: string;
  profileImage: string;
  //publications: Publication[];
  //comments: Comment[];
}
