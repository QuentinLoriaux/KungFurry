import RealisateurCard from "./RealisateurCard";

export default function RealisateurList(props) {
    return (
        <div style={{display: "flex", flexWrap: "wrap", gap: "20px"}}>
            {props.realisateurs.map(realisateur => (
                <RealisateurCard key={realisateur.id} realisateur={realisateur} setRealisateurs={props.setRealisateurs}/>
            ))}
        </div>
    )
}