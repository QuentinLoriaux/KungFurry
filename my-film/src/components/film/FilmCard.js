import React, {useState} from 'react';
import Card from '@mui/material/Card';
import CardContent from '@mui/material/CardContent';
import Typography from '@mui/material/Typography';
import IconButton from '@mui/material/IconButton';
import DeleteIcon from '@mui/icons-material/Delete';
import EditIcon from '@mui/icons-material/Edit';
import Dialog from '@mui/material/Dialog';
import DialogTitle from '@mui/material/DialogTitle';
import DialogContent from '@mui/material/DialogContent';
import { deleteFilm, putFilm} from '../../api/FilmApi';
import CreateFilmForm from './CreateFilmForm';
import {useNavigate} from "react-router-dom";
import {Grade, RemoveRedEyeRounded} from "@mui/icons-material";
import {Box, Icon} from "@mui/material";




export default function FilmCard(props) {
    const [open, setOpen] = useState(false);
    const role = props.token && props.token.token ? props.token.token.split(';')[1].split('.')[0] : -1;
    const handleClickOnEditButton = (film) => {
        setOpen(true); // Open the dialog
    };
    const handleClose = () => {
        setOpen(false); // Close the dialog
    };

    const handleEditSubmit = (updatedFilm) => {
        putFilm(props.film.id ,updatedFilm, props.token).then(response => {
            props.setFilms((prevFilms) => {
                const updatedFilms = prevFilms.map((film) =>
                    film.id === response.data.id ? response.data : film
                );
                props.applyFilters(updatedFilms);
                return updatedFilms;
            });
            handleClose();
        }).catch(error => {
            console.error('Error editing film:', error);
            alert('An error occurred while editing the film.');
            handleClose();
        });
    };

    const handleClickOnDeleteButton = () => {
        const id = props.film.id;
        deleteFilm(id, props.token)
            .then(() => {
                props.setFilms((prevFilms) => {
                    const updatedFilms = prevFilms.filter((film) => film.id !== id);
                    props.applyFilters(updatedFilms);
                    return updatedFilms;
                });
            })
            .catch((error) => {
                console.error('Erreur lors de la suppression du film:', error);
                alert('Une erreur est survenue lors de la suppression du film.');
            });
    };

    const navigate = useNavigate();

    const handleCardClick = () => {
        navigate(`/film/${props.film.id}`);
    };


    return (
    <Card variant="outlined" sx={{ margin: 2, maxWidth: 300 }}>
      <CardContent onClick={handleCardClick}>
        <Typography variant="h5" gutterBottom>
          {props.film.titre}
        </Typography>
        <Typography variant="body1">
          {props.film.duree} minutes
        </Typography>
          <Box sx={{ display: "flex", alignItems: "center", justifyContent: "space-between", marginTop: 1 }}>
              <Box sx={{ display: "flex", alignItems: "center", gap: 0.5 }}>
                  <Grade color={"warning"} />
                  <Typography variant="body2">{props.film.noteMoyenne}</Typography>
              </Box>
              <Box sx={{ display: "flex", alignItems: "center", gap: 0.5 }}>
                  <RemoveRedEyeRounded />
                  <Typography variant="body2">{props.film.nbVues === undefined ? 0 : props.film.nbVues}</Typography>
              </Box>
          </Box>
      </CardContent>
        { role == 2 ?
            <IconButton onClick={handleClickOnDeleteButton}>
                <DeleteIcon/>
            </IconButton> : null}
        { role == 2 ?
            <IconButton onClick={handleClickOnEditButton}>
                <Dialog onClose={handleClose} open={open}>
                    <DialogTitle>Editer un film</DialogTitle>
                    <DialogContent>
                        <CreateFilmForm film={props.film} onSubmit={handleEditSubmit}/>
                    </DialogContent>
                </Dialog>
                <EditIcon/>
            </IconButton> : null}

    </Card>
    );
}
