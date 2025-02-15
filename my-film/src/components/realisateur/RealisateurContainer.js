import CreateRealisateurForm from "./CreateRealisateurForm";
import RealisateurList from "./RealisateurList";
import { useState, useEffect } from "react";
import {getAllRealisateurs, postRealisateur} from "../../api/RealisateurApi";

const RealisateurContainer = (token) => {
    const [realisateurs, setRealisateurs] = useState([])

    useEffect(() => {
        getAllRealisateurs(0,25).then(response => {
            setRealisateurs(response.data.data)
        }).catch(err => {
            console.log(err);
        })
    }, []);

    const handleCreateRealisateur = (newRealisateur) => {
        postRealisateur(newRealisateur, token).then(response => {
            setRealisateurs([...realisateurs, response.data])
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
                <RealisateurList realisateurs={realisateurs} setRealisateurs={setRealisateurs} token={token}/>
            </div>
        </div>
    );
}

export default RealisateurContainer