import React from 'react';
import Card from '@mui/material/Card';
import CardContent from '@mui/material/CardContent';
import Typography from '@mui/material/Typography';
import IconButton from '@mui/material/IconButton';
import DeleteIcon from '@mui/icons-material/Delete';
import EditIcon from '@mui/icons-material/Edit';
import Dialog from '@mui/material/Dialog';
import DialogTitle from '@mui/material/DialogTitle';
import DialogContent from '@mui/material/DialogContent';
import { deleteFilm, putFilm, getAllFilms } from '../api/FilmApi';
import CreateFilmForm from './CreateFilmForm';




export default function FilmCard(props) {
  const [open, setOpen] = React.useState(false);

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
    putFilm(updatedFilm).then(response => {
        // Update the films list with the edited film
        //setFilms(films.map(film => film.id === updatedFilm.id ? updatedFilm : film));
        //films.map(film => film.id === updatedFilm.id ? updatedFilm : film);
        handleClose(); // Close the dialog after editing
    }).catch(error => {
        console.error('Error editing film:', error);
        handleClose(); // Close the dialog even if an error occurred
    });
};

const handleClickOnDeleteButton = () => {
    const id = props.film.id;
    console.log('Delete film with ID:', id);
    deleteFilm(id).then(() => {

        //setFilms(films.filter(film => film.id !== id)); // Remove the deleted film from the list
    }).catch(error => {
        console.error('Error deleting film:', error);
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
