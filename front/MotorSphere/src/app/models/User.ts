import { Publication } from "./Publication";

export interface User {
    userId: number;
    email: string;
    name: string;
    lastName: string;
    birthDate: Date;
    phone: string;
    profileCreateDate: Date;
    biography: string;
    profileImage: Uint8Array;
    publications: Publication[];
    comments: Comment[];
}