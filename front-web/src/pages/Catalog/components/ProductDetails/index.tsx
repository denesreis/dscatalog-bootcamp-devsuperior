import { useParams } from 'react-router-dom';
import './styles.scss';

type ParamsType = {
    productId : string;
}

const ProductDetails = () => {
    const {productId} = useParams<ParamsType>();

    return (
        <div className = "product-details-container">
            <h1>
                Destalhes do produto
            </h1>

        </div>
    )
}

export default ProductDetails