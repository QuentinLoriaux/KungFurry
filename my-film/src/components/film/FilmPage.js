import {useEffect, useState} from "react";
import {useParams} from "react-router-dom";
import {getFilmById} from "../../api/FilmApi";
import "../../styles/FilmPage.css";
import {Box, Button, CardMedia, Rating, TextField} from "@mui/material";
import Typography from "@mui/material/Typography";
import CardContent from "@mui/material/CardContent";
import Card from "@mui/material/Card";


function FilmPage() {
    const { id } = useParams();
    const [film, setFilm] = useState(null);
    const [userRating, setUserRating] = useState(null); // Note donnée par l'utilisateur
    const [comments, setComments] = useState([]);
    const [newComment, setNewComment] = useState("");
    const [editingIndex, setEditingIndex] = useState(null);
    const [editText, setEditText] = useState("");
    const currentUser = "Moi";

    useEffect(() => {
        getFilmById(id)
            .then(response => {
                setFilm(response.data);
            })
            .catch(error => {
                console.error('Erreur lors de la récupération du film:', error);
            });
    }, [id]);

    if (!film) return <p>Chargement...</p>;

    const handleRate = (event, newValue) => {
        setUserRating(newValue);
    };

    const addComment = () => {
        if (newComment.trim()) {
            const newCommentObj = {
                author: currentUser,
                text: newComment,
                date: new Date().toISOString(),
            };
            setComments([...comments, newCommentObj]);
            setNewComment("");
        }
    };

    const editComment = (index) => {
        setEditingIndex(index);
        setEditText(comments[index].text);
    };

    const saveEdit = () => {
        const updatedComments = [...comments];
        updatedComments[editingIndex].text = editText;
        setComments(updatedComments);
        setEditingIndex(null);
    };

    const deleteComment = (index) => {
        const updatedComments = comments.filter((_, i) => i !== index);
        setComments(updatedComments);
    };

    const handleRealisateurRedirect = () => {
        // Rediriger vers la page du réalisateur
    }

    if (!film) return <p>Chargement...</p>;

    return (
        <div className="film-container">
            {/* Bloc principal : Infos + Image */}
            <div className="film-content">
                {/* Bloc de gauche : Infos du film */}
                <Card className="film-info">
                    <CardContent>
                        <Typography variant="h4">{film.titre}</Typography>
                        <Typography variant="body1" className="film-description">{film.description}</Typography>

                        <Typography>⏳ Durée : {film.duree} min</Typography>
                        <Box display="flex" alignItems="center">
                            <Typography>⭐ Note :</Typography>
                            <Rating value={film.noteMoyenne} readOnly />
                        </Box>
                        <Typography>📖 Genre : {film.genre.name}</Typography>
                        <Typography>🗓️ Date de sortie : {film.dateSortie}</Typography>
                        <Typography onClick={handleRealisateurRedirect}>🎬 Réalisateur : {film.realisateur.prenom} {film.realisateur.nom}</Typography>

                        <Box display="flex" alignItems="center">
                            <Typography>📝 Votre note :</Typography>
                            <Rating value={userRating} onChange={handleRate} />
                        </Box>

                        <Box display="flex" alignItems="center">
                            <Button variant="contained" color="primary" className="watch-button">
                                🎥 Regarder le film
                            </Button>
                            <Button variant="outlined" color="secondary" className="add-to-list">
                                📌 Ajouter à ma liste
                            </Button>
                        </Box>
                    </CardContent>
                </Card>

                {/* Bloc de droite : Image */}
                <Card className="film-cover">
                    <CardMedia component="img" image={film.cover} alt="Couverture du film" />
                </Card>
            </div>

            <div className="comments-section">
                <Typography variant="h5">💬 Commentaires :</Typography>

                {comments.map((comment, index) => (
                    <Card key={index} className="comment">
                        <CardContent>
                            <Box display="flex" justifyContent="space-between">
                                <Typography variant="subtitle2">
                                    🧑 {comment.author} - {new Date(comment.date).toLocaleDateString()}
                                </Typography>

                                {comment.author === currentUser && (
                                    <Box>
                                        <Button size="small" color="primary" onClick={() => editComment(index)}>
                                            Modifier
                                        </Button>
                                        <Button size="small" color="secondary" onClick={() => deleteComment(index)}>
                                            Supprimer
                                        </Button>
                                    </Box>
                                )}
                            </Box>

                            {editingIndex === index ? (
                                <TextField
                                    fullWidth
                                    variant="outlined"
                                    value={editText}
                                    onChange={(e) => setEditText(e.target.value)}
                                />
                            ) : (
                                <Typography>{comment.text}</Typography>
                            )}

                            {/* Bouton Enregistrer la modification */}
                            {editingIndex === index && (
                                <Button size="small" color="success" onClick={saveEdit}>
                                    Enregistrer
                                </Button>
                            )}
                        </CardContent>
                    </Card>
                ))}

                <TextField
                    label="Ajouter un commentaire"
                    variant="outlined"
                    fullWidth
                    value={newComment}
                    onChange={(e) => setNewComment(e.target.value)}
                />
                <Button variant="contained" color="primary" onClick={addComment}>
                    Ajouter
                </Button>
            </div>
        </div>
    );
}

export default FilmPage;
