import { Publication } from "./Publication";
import { User } from "./User";

export interface Comment {
    commentId: number;
    user: User;
    date: Date;
    information: string;
    publication: Publication;
}