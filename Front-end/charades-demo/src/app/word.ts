import { UUID } from "crypto";

export class Word {
    id!: UUID;
    word!: string;
    description!: string;
    letterCount!: number;
}
