import React from 'react';
import './App.css';
import Header from './Header';
import FilmCard from './components/FilmCard'


function App() {
    const films = [
        { titre: 'Inception', duree: 148 },
        { titre: 'The Matrix', duree: 136 },
        { titre: 'Interstellar', duree: 169 },
      ];

  return (
    <div>
      <Header />
      <div style={{ display: 'flex', flexWrap: 'wrap', justifyContent: 'center' }}>
        {films.map((film, index) => (
          <FilmCard key={index} film={film} />
        ))}
      </div>
    </div>
  );
}

export default App;


