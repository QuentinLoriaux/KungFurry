import CreateFilmForm from "./CreateFilmForm";
import FilmList from "./FilmList";
import { useState, useEffect } from "react";
import { getAllFilms, postFilm } from "../../api/FilmApi";

const FilmContainer = () => {
    const [films, setFilms] = useState([]);

    useEffect(() => {
        getAllFilms().then(reponse => {
            setFilms(reponse.data);
        }).catch(err => {
            console.log(err);
        })
    }, []);

    const handleCreateFilm = (newFilm) => {
         postFilm(newFilm)
            .then((response) => {
                setFilms([...films, response.data]);
            })
            .catch((error) => {
                console.error('Erreur lors de la création du film:', error);
                alert('Une erreur est survenue lors de l’ajout du film.');
            });
    }

    
    return (
        <div style={{display: "flex", alignItems: "flex-start"}}>
            <div style={{width: "400px", margin: "20px"}}>
                <CreateFilmForm film={null} onSubmit={handleCreateFilm}/>
            </div>
            <div style={{flex: 1, marginLeft: "20px", display: "flex", justifyContent: "center"}}>
                <FilmList films={films} setFilms={setFilms}/>
            </div>
        </div>

    );
}

export default FilmContainer;