import logo from './logo.svg';
import './App.css';
import 'bootstrap/dist/css/bootstrap.css'
import Header from './components/header';
import Main from './components/main';
import Footer from './components/footer';

function App() {
  return (
    <div className="App">
     <Header name=" "></Header>
     <Main />
     <Footer name="UNLP - Maestría en Ingeniería de Software - Victor Martinez" />
  </div>
  );
}

export default App;
