import { useEffect, useState } from 'react';
import { Link } from 'react-router-dom';
import { ProductsResponse } from '../../core/types/Product';
import { makeRequest } from '../../core/utils/request';
import ProductCard from './components/ProductCard'
import './styles.scss'
//mport axios from 'axios';

const Catalog = () => {
    //Quando o componente iniciar buscar a lista de produtos

    //Quando a lista de produtos estiver disponível popular um estado no componente e listar dinamicamente

    //fetch = Função nativa do java script para fazer requisições
        //Limitações: Muito verboso
        //Não tem suporte nativo para ler progresso de upload de arquivo
        //Não tem suporte nativo para enviar query strings

  /*  useEffect( () => {
        fetch('http://localhost:3000/products')
        .then(response => response.json())
        .then(response => console.log(response));
    },[]);*/
 /*     useEffect(() => {
        axios('http://localhost:3000/products')
          .then(response => console.log(response));
      },[]); */

      const [productsResponse, setProductsResponse ] = useState<ProductsResponse>();

      console.log(productsResponse);

      useEffect(() => {
        const params = {
            page: 0,
            linesPerPage: 12
       }
        makeRequest({url: '/products',params})
//          .then(response => console.log(response));
            //.data é uma estrutura que o axios retorna
            .then(response => setProductsResponse(response.data))
      },[]);
  

    return (
        <div className="catalog-container">
            <h1 className="catalog-title">
                Catálogo de Produtos
            </h1>
            <div className="catalog-products">
                {productsResponse?.content.map(product => (
                    <Link to = {`/products/${product.id}`} key={product.id}>
                        <ProductCard product={product}/> 
                    </Link>

                ))}

    
            </div>
        </div>
    
    );
}

export default Catalog