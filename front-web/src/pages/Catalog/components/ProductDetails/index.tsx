import { Link, useParams } from 'react-router-dom';
import './styles.scss';
import {ReactComponent as ArrowIcon} from '../../../../core/assets/images/arrow.svg'
//import {ReactComponent as ProductImage} from '../../../../core/assets/images/product.svg'
import ProductPrice from '../ProductPrice';
import { useEffect, useState } from 'react';
import { makeRequest } from '../../../../core/utils/request';
import { Product } from '../../../../core/types/Product';

type ParamsType = {
    productId : string;
}


const ProductDetails = () => {
    const {productId} = useParams<ParamsType>();
    const [product, setproduct] = useState<Product>();

    useEffect(()=> {
        makeRequest({url:`/products/${productId}`})
        .then(response => setproduct(response.data))

    },[productId]);
    //console.log('Produto:'+productId);
    /*row = classe do bootstrap e já tem um display flex embutido
    text-center= classe do bootstrap 
    Quando é colocado col-6 significa que vai ocupar metade da tela porque o bootstrap separa a tela em 12
    pr-5 - padding right do bootstrap*/
    return (
        <div className = "product-details-container">
            <div className="card-base border-radius-20 product-details">
                <Link to='/products' className="product-details-goback">
                    <ArrowIcon className="icon-goback"/>
                    <h1 className="text-goback">voltar</h1>  
                </Link>
                <div className="row">
                    <div className="col-6 pr-5">
                        <div className="product-details-card text-center">
                            <img src={product?.imgUrl} alt={product?.name} className="product-details-image" / >
                        </div>
                        <h1 className="product-details-name">
                        {product?.name}
                        </h1>
                       {product?.price &&  <ProductPrice price ={product?.price}/>}
                     </div>
                    <div className="col-6 product-details-card">
                        <h1 className="product-description-title">
                            Descrição do Produto
                        </h1>
                        <p className="product-description-text">
                        {product?.description}
                        </p>
                    </div>

                </div>

                
      
            </div>


        </div>
    )
}

export default ProductDetails