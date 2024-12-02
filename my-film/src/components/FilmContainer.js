import CreateFilmForm from "./CreateFilmForm";
import FilmList from "./FilmList";
import { useState, useEffect } from "react";
import { getAllFilms, postFilm } from "../api/FilmApi";

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
                console.log('Film created successfully:', response.data);
                alert('Film ajouté avec succès');
            })
            .catch((error) => {
                console.error('Erreur lors de la création du film:', error);
                alert('Une erreur est survenue lors de l’ajout du film.');
            });
    }

    
    return (
        <div>
            <CreateFilmForm film={null} onSubmit={handleCreateFilm}/>
            <FilmList films={films} />
        </div>
    );
}

export default FilmContainer;