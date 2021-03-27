import axios,{Method} from 'axios';

type RequestParams ={
    //Method é um tipo do axios
    //Colocando o ? indica que o parametro não é obrigatório
    method?:Method;
    url: string;
    data?: object;
    params?: object;
}

const BASE_URL = 'http://localhost:3000';

export const makeRequest = ({method = 'GET',url,data,params}:RequestParams) => {
    return axios({
        method,
        //notação de template string usando ``
        // o sinal de vai concatenando os valores
        url:`${BASE_URL}${url}`,
        data,
        params

    });
}