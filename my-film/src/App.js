import React, {useState} from 'react';
import './App.css';
import Header from './Header';
import {BrowserRouter as Router, Routes, Route} from 'react-router-dom';
import RealisateurContainer from './components/realisateur/RealisateurContainer';
import FilmContainer from './components/film/FilmContainer';
import FilmPage from './components/film/FilmPage';
import UserContainer from "./components/user/UserContainer";

function App() {
    const [isLoggedIn, setIsLoggedIn] = useState(false);
    const [authData, setAuthData] = useState(null);

    const handleLogout = () => {
        setIsLoggedIn(false);
        setAuthData(null);
        alert('Déconnexion réussie!');
    };

    return (
        <Router>
            <div>
                <Header setIsLoggedIn={setIsLoggedIn} isLoggedIn={isLoggedIn} handleLogout={handleLogout}/>
                <Routes>
                    <Route
                        path="/"
                        element={
                            <div style={{ display: 'flex', flexWrap: 'wrap', justifyContent: 'left' }}>
                                <FilmContainer />
                            </div>
                        }
                    />
                    <Route
                        path="/realisateurs"
                        element={
                            <div style={{ display: 'flex', flexWrap: 'wrap', justifyContent: 'left' }}>
                                <RealisateurContainer />
                            </div>
                        }
                    />
                    <Route
                        path="/users"
                        element={
                            <div style={{ display: 'flex', flexWrap: 'wrap', justifyContent: 'center' }}>
                                <UserContainer />
                            </div>
                        }
                    />
                    <Route
                        path="/film/:id"
                        element={
                            <FilmPage/>
                        }
                    />
                </Routes>
            </div>
        </Router>
    );
}

export default App;

