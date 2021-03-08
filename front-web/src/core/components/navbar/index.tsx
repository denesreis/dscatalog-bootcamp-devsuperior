import React from 'react';
import './styles.scss';

const Navbar = () => (
    //Nav usado para barra de navegação melhor que usar a div apesar de ser semelhante
    //primary já esta customizado no custom como azul
    /*O bootstrap divide a tela em 12 colunas neste caso esta fazendo referencia a 2 coluna*/
    /*.col-6 + Tab monta a div automaticamente */
    /*href indica que é link */
    /*offset-2 coloca um recuo de duas colunas a esquerda */
    <nav className = "row bg-primary main-nav">
        <div className = "col-2"> 
        <a href="link" className="nav-log-text">
        <h4>DS Catalog</h4>
        </a>
        </div>
        <div className="col-6 offset-2">
            <ul className="main-menu">
                <li>
                    <a href="link" className="active">
                        HOME
                    </a>
                </li>
                <li>
                    <a href="link">
                        CATÁLOGO
                    </a>
                </li>                
                <li>
                    <a href="link">
                        ADMIN
                    </a>
                </li>
            </ul>

        </div>
      
    </nav>
);

export default Navbar;