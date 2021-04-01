import ContentLoader from "react-content-loader"
import { generateList } from "core/utils/list"




const ProductCardLoader = () => {
    //Para criar uma lista de 3 elementos
    const loaderItems = generateList(5);

    return (
        /* <> </> é chamado de fragment e serve para retornar mais de uma div  que no caso abaixo vai ser gerada
        pela map */
        <>
            {/*Para cada item da lista executa o código */}
            {loaderItems.map(item => (
                <ContentLoader
                    key={item}
                    speed={1}
                    width={250}
                    height={335}
                    viewBox="0 0 250 335" 
                    backgroundColor="#ecebeb"
                    foregroundColor="#d6d2d2"
                >
                    <rect x="0" y="0" rx="10" ry="10" width="250" height="335" />
                </ContentLoader>

            ))}
        </>
    )
}

export default ProductCardLoader