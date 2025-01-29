import {useEffect, useState} from "react";
import {useParams} from "react-router-dom";
import {getFilmById} from "../../api/FilmApi";

function FilmPage() {
    const { id } = useParams();
    const [film, setFilm] = useState(null);

    useEffect(() => {
        getFilmById(id)
            .then(response => {
                setFilm(response.data);
            })
            .catch(error => {
                console.error('Erreur lors de la récupération du film:', error);
            });
    }, [id]);

    if (!film) return <p>Chargement...</p>;
    return (
        <div>
            <h1>{film.titre}</h1>
            <p>Durée : {film.duree} minutes</p>
            <p>Réalisateur : {film.realisateur.prenom} {film.realisateur.nom} {film.realisateur.celebre ? "(Célèbre)" : ""}</p>
            <p>Genre : {film.genre.name}</p>
            <p>Note moyenne : {film.noteMoyenne}</p>
        </div>
    );
}

export default FilmPage;