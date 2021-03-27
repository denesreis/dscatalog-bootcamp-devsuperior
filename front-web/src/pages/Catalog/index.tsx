import { useEffect } from 'react';
import { Link } from 'react-router-dom';
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

      useEffect(() => {
        const params = {
            page: 0,
            linesPerPage: 12
       }
        makeRequest({url: '/products',params})
          .then(response => console.log(response));
      },[]);
  

    return (
        <div className="catalog-container">
            <h1 className="catalog-title">
                Catálogo de Produtos
            </h1>
            <div className="catalog-products">
                <Link to="/products/1"><ProductCard /></Link>
                <Link to="/products/2"><ProductCard /></Link>
                <Link to="/products/3"><ProductCard /></Link>
                <Link to="/products/4"><ProductCard /></Link>
                <Link to="/products/5"><ProductCard /></Link>
                <Link to="/products/6"><ProductCard /></Link>
                <Link to="/products/7"><ProductCard /></Link>
                <Link to="/products/8"><ProductCard /></Link>
                <Link to="/products/9"><ProductCard /></Link>
    
            </div>
        </div>
    
    );
}

export default Catalog