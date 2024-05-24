import { User } from "./User";

export interface Publication {
    publicationId: number;
    name: string;
    uploadDate: string;
    likes: number;
    image: Uint8Array;
    comments: Comment[];
    information: string;
    user: User;
}