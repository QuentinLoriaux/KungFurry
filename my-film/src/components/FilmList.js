import FilmCard from "./FilmCard";

export default function FilmList(props) {
    return (
        <div style={{ display: "flex", flexWrap: "wrap", gap: "20px" }}>
            {props.films.map((film) => (
                <FilmCard key={film.id} film={film} setFilms={props.setFilms} />
            ))}
        </div>
    );
}

;