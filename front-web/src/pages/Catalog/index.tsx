import ProductCard from './components'
import './styles.scss'

const Catalog = () => (
    <div className="catalog-container">
        <h1 className="catalog-title">
            Catálogo de Produtos
        </h1>
        <div className="catalog-products">
            <ProductCard />
            <ProductCard />
            <ProductCard />
            <ProductCard />
            <ProductCard />
            <ProductCard />
            <ProductCard />
            <ProductCard />
            <ProductCard />
            <ProductCard />
            <ProductCard />
            <ProductCard />            

        </div>
    </div>

);

export default Catalog