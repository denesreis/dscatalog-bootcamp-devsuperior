//Objeto para guardar o retorno da requisição
export type ProductsResponse = {
    content: Product[];
    totalPages: number;

}

export type Product = {
    id: number;
    name: string;
    description: string;
    price: number;
    imgUrl: string;
    date: string;
    categories: Category[];
}

export type Category = { 
    id: number;
    name: string;
}