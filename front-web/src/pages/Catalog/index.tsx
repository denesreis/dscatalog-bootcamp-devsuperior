import { useEffect, useState } from 'react';
import { Link } from 'react-router-dom';
import { ProductsResponse } from 'core/types/Product';
import { makeRequest } from 'core/utils/request';
import ProductCard from './components/ProductCard'
import ProductCardLoader from './components/Loaders/ProductCardLoader';
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
      //useState(false) -> começa com false
      const [isLoading, setIsLoading] = useState(false);

      console.log(productsResponse);

      useEffect(() => {
        const params = {
            page: 0,
            linesPerPage: 12
       }
       setIsLoading(true); //antes de fazer a request apresenta o loader
        makeRequest({url: '/products',params})
//          .then(response => console.log(response));
            //.data é uma estrutura que o axios retorna
            .then(response => setProductsResponse(response.data))
            .finally(()=> {
                //Finaliza o loader
                setIsLoading(false)

            })
      },[]);
  

    return (
        <div className="catalog-container">
            <h1 className="catalog-title">
                Catálogo de Produtos
            </h1>
            <div className="catalog-products">
                {/*Se esta carregando mostra o loarder senão mostra o card do produto*/}
                {isLoading ? <ProductCardLoader /> : (
                    productsResponse?.content.map(product => (
                    <Link to = {`/products/${product.id}`} key={product.id}>
                        <ProductCard product={product}/> 
                    </Link>
                ))   
                )}
    
            </div>
        </div>
    
    );
}

export default Catalog