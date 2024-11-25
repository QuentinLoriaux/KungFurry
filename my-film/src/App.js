import React from 'react';
import './App.css';
import Header from './Header';
import RealisateurList from './components/RealisateurList';
import FilmContainer from './components/FilmContainer';

function App() {

  return (
    <div>
      <Header />
      <div style={{ display: 'flex', flexWrap: 'wrap', justifyContent: 'center' }}>
        <FilmContainer />
      </div>
      <div style={{ display: 'flex', flexWrap: 'wrap', justifyContent: 'center' }}>
        <RealisateurList/>
      </div>
    </div>
  );
}

export default App;


