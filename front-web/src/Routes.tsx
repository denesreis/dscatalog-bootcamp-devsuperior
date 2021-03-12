import React from 'react';
//BrowserRouter -> encapsula toda a aplicação vai gerencia toda a aplicação
//Switch -> Vai decidir qual rota tem que reinderizar
//Route -> é através do router que se define qual a url de cada rota da navegação
import {BrowserRouter,Switch,Route} from 'react-router-dom';
import Navbar from './core/components/navbar';
import Admin from './pages/Admin';
import Catalog from './pages/Catalog';
import ProductDetails from './pages/Catalog/components/ProductDetails';
import Home from './pages/Home';

const Routes = () => (
    <BrowserRouter>
        <Navbar />
        <Switch>
            <Route path="/" exact>
                <Home />
|           </Route>
            <Route path="/products" exact>
                <Catalog />
            </Route>
            <Route path="/products/:productId">
                <ProductDetails />
            </Route>
            <Route path="/admin">
                <Admin />
            </Route>
        </Switch>


    </BrowserRouter>


);

export default Routes;