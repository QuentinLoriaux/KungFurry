import React, {useEffect, useState} from 'react';
import Card from '@mui/material/Card';
import CardContent from '@mui/material/CardContent';
import Typography from '@mui/material/Typography';
import IconButton from '@mui/material/IconButton';
import DeleteIcon from '@mui/icons-material/Delete';
import EditIcon from '@mui/icons-material/Edit';
import Dialog from '@mui/material/Dialog';
import DialogTitle from '@mui/material/DialogTitle';
import DialogContent from '@mui/material/DialogContent';
import { deleteFilm, putFilm, getAllFilms } from '../../api/FilmApi';
import CreateFilmForm from './CreateFilmForm';




export default function FilmCard(props) {
    const [open, setOpen] = React.useState(false);
    const [films, setFilms] = useState([]);

    useEffect(() => {
        getAllFilms().then(reponse => {
            setFilms(reponse.data);
        }).catch(err => {
            console.log(err);
        })
    }, []);

    // Handle the opening of the dialog
    const handleClickOnEditButton = (film) => {
        setOpen(true); // Open the dialog
    };

    // Handle closing of the dialog
    const handleClose = () => {
        setOpen(false); // Close the dialog
    };

    // Handle the film edit submit
    const handleEditSubmit = (updatedFilm) => {
        putFilm(props.film.id ,updatedFilm).then(response => {
            console.log('Film edited successfully:', response.data);
            props.setFilms((prevFilms) => prevFilms.map((film) => {
                if (film.id === response.data.id) {
                    return response.data;
                }
                return film;
            }));
            handleClose();
        }).catch(error => {
            console.error('Error editing film:', error);
            alert('An error occurred while editing the film.');
            handleClose(); // Close the dialog even if an error occurred
        });
    };

    const handleClickOnDeleteButton = () => {
        const id = props.film.id;
        deleteFilm(id)
            .then(() => {
                props.setFilms((prevFilms) => prevFilms.filter((film) => film.id !== id));
            })
            .catch((error) => {
                console.error('Erreur lors de la suppression du film:', error);
                alert('Une erreur est survenue lors de la suppression du film.');
            });
    };


    return (
    <Card variant="outlined" sx={{ margin: 2, maxWidth: 300 }}>
      <CardContent>
        <Typography variant="h5" gutterBottom>
          {props.film.titre}
        </Typography>
        <Typography variant="body1">
          {props.film.duree} minutes
        </Typography>
      </CardContent>

    <IconButton onClick={handleClickOnDeleteButton}>
        <DeleteIcon/>
    </IconButton>
    <IconButton onClick={handleClickOnEditButton}>
          <Dialog onClose={handleClose} open={open}>
          <DialogTitle>Editer un film</DialogTitle>
          <DialogContent>
              <CreateFilmForm film={props.film} onSubmit={handleEditSubmit}/>
          </DialogContent>
          </Dialog>
          <EditIcon/>
    </IconButton>
    </Card>
    );
}
