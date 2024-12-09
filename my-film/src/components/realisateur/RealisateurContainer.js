import CreateRealisateurForm from "./CreateRealisateurForm";
import RealisateurList from "./RealisateurList";
import { useState, useEffect } from "react";
import {getAllRealisateurs, postRealisateur} from "../../api/RealisateurApi";

const RealisateurContainer = () => {
    const [realisateurs, setRealisateurs] = useState([])

    useEffect(() => {
        getAllRealisateurs().then(response => {
            setRealisateurs(response.data)
        }).catch(err => {
            console.log(err);
        })
    }, []);

    const handleCreateRealisateur = (newRealisateur) => {
        postRealisateur(newRealisateur).then(response => {
            setRealisateurs(response.data)
        }).catch(err => {
            alert("Une erreur est survenue lors de l'ajout du réalisateur")
            console.log("Erreur lors de l'ajout du réalisateur",err)
        })
    }

    return (
        <div style={{display: "flex", alignItems: "flex-start"}}>
            <div style={{width: "400px", margin: "20px"}}>
                <CreateRealisateurForm realisateur={null} onSubmit={handleCreateRealisateur}/>
            </div>
            <div style={{flex: 1, marginLeft: "20px", display: "flex", justifyContent: "center"}}>
                <RealisateurList realisateurs={realisateurs} setRealisateurs={setRealisateurs}/>
            </div>
        </div>
    );
}

export default RealisateurContainer