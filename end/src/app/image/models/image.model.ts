export interface Image {
  id?: number;
  url: string;
  analysed_at: Date;
  tags: Map<string,number>;
  height: number;
  width: number;
}
