import './App.css';
import 'react-bootstrap';
import { Footer } from './componets/footer/Footer';
import { NavBar } from './componets/navbar/NavBar';
import { Content } from './componets/content/Content';

function App() {
  return (
    <div className="App">
      <NavBar />
      <Content />
      <Footer />
    </div>
  );
}

export default App;
