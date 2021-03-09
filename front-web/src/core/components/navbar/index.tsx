import React from 'react';
import './styles.scss';
import { Link,NavLink } from 'react-router-dom';

const Navbar = () => (
    //Nav usado para barra de navegação melhor que usar a div apesar de ser semelhante
    //primary já esta customizado no custom como azul
    /*O bootstrap divide a tela em 12 colunas neste caso esta fazendo referencia a 2 coluna*/
    /*.col-6 + Tab monta a div automaticamente */
    /*href indica que é link */
    /*offset-2 coloca um recuo de duas colunas a esquerda */
    /*activeClassName = o react-router se encarrega de gerenciar a classe ativa */
    <nav className = "row bg-primary main-nav">
        <div className = "col-2"> 
        <Link to="/" className="nav-log-text">
        <h4>DS Catalog</h4>
        </Link>
        </div>
        <div className="col-6 offset-2">
            <ul className="main-menu">
                <li>
                    <NavLink to ="/" activeClassName="active" exact>
                        HOME
                    </NavLink>
                </li>
                <li>
                    <NavLink to="/catalog" activeClassName="active">
                        CATÁLOGO
                    </NavLink>
                </li>                
                <li>
                    <NavLink to="/admin" activeClassName="active">
                        ADMIN
                    </NavLink>
                </li>
            </ul>

        </div>
      
    </nav>
);

export default Navbar;