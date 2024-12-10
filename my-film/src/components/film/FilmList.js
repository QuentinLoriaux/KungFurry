import FilmCard from "./FilmCard";

export default function FilmList(props) {
    if (!props.films || props.films.length === 0) {
        return <p>Aucun film à afficher.</p>;
    }
    return (
        <div style={{ display: "flex", flexWrap: "wrap", gap: "20px" }}>
            {props.films.map((film) => (
                <FilmCard key={film.id} film={film} setFilms={props.setFilms} />
            ))}
        </div>
    );
}

;