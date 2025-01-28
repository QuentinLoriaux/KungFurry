import RealisateurCard from "./RealisateurCard";

export default function RealisateurList(props) {
    if (!props.realisateurs || props.realisateurs.length === 0) {
        return <p>Aucun réalisateur à afficher.</p>;
    }
    return (
        <div style={{display: "flex", flexWrap: "wrap", gap: "20px"}}>
            {props.realisateurs.map(realisateur => (
                <RealisateurCard key={realisateur.id} realisateur={realisateur} setRealisateurs={props.setRealisateurs} token={props.token}/>
            ))}
        </div>
    )
}