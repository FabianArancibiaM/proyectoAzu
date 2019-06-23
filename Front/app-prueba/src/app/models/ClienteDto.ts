import { RegionDto } from './RegionDto';

export class ClienteDto{
    id:Number;

    nombre:string;
   
    apellido:string;
   
    email:string;
   
    createdAt:Date;
   
    foto:string;
   
    regionDto:RegionDto;
}
 

