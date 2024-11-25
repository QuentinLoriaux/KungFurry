import React from 'react';
import Card from '@mui/material/Card';
import CardContent from '@mui/material/CardContent';
import Typography from '@mui/material/Typography';

export default function FilmCard(props) {
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
    </Card>
  );
}
