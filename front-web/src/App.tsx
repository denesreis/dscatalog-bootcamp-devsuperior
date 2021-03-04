import React,{ useState,useEffect } from 'react';
//useState - react hoots mais comum
//useEffect - Outro reac hoots acessa o ciclo de vida do componente
//          Primeiro parametro é uma arrow function e o segundo um array de dependencia. array vazio executa somente uma vez

const App = () => {
    //Counter(variável) é o valor do estado setCounte=função que alterar o estado
    const [counter, setCounter] = useState(0);
    useEffect (() => {
        console.log('Componente iniciado'+'- O componente mudou de valor: '+counter)
    },[counter])
    return (
        //mt = Margem Top ml = margem esquerda
        //btn-primary -e um tipo do bootstrap
        <div className="container mt-5">
            <button className="btn btn-primary mr-5"
            onClick={() => setCounter(counter + 1)}
            >
                +
            </button>
            <span>
                {counter}
            </span>
            <button className = "btn btn-primary ml-5"
            onClick={() => setCounter(counter - 1)}
            >
                -
            </button>
            {counter > 5 && <h1>Valor maior que 5</h1>}
            {counter <= 5 && <h1>Valor menor ou igual 5</h1>}
   
   
        </div>
    );
}
export default App;