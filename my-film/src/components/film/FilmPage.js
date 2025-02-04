import {useEffect, useState} from "react";
import {useParams} from "react-router-dom";
import {getFilmById} from "../../api/FilmApi";
import {postNote, deleteNote, putNote} from "../../api/NoteApi";
import {postComment, deleteCommentAPI, putComment} from "../../api/CommentaireApi";
import "../../styles/FilmPage.css";
import {Box, Button, CardMedia, Rating, TextField} from "@mui/material";
import Typography from "@mui/material/Typography";
import CardContent from "@mui/material/CardContent";
import Card from "@mui/material/Card";


function FilmPage(token) {
    const { id } = useParams();
    const [film, setFilm] = useState(null);
    const [userRating, setUserRating] = useState(null); // Note donnée par l'utilisateur
    const [comments, setComments] = useState([]);
    const [newComment, setNewComment] = useState("");
    const [editingIndex, setEditingIndex] = useState(null);
    const [editText, setEditText] = useState("");
    const currentUser = token && token.token ? token.token.split(';')[0] : null;

    useEffect(() => {
        getFilmById(id)
            .then(response => {
                setFilm(response.data);
                setComments(response.data.commentaires);
            })
            .catch(error => {
                console.error('Erreur lors de la récupération du film:', error);
            });
    }, [id]);

    if (!film) return <p>Chargement...</p>;

    const handleRate = (event, newValue) => {
        setUserRating(newValue);
        postNote(newValue, token, id).then()
    };

    const addComment = () => {
        if (newComment.trim()) {

            postComment(newComment, token, id).then(
                response => {
                    const newCommentObj = response.data;
                    setComments([...comments, newCommentObj]);
                    setNewComment("");
                }
            ).catch(error => {
                console.error('Erreur lors de la création du commentaire', error);
            })
        }
    };

    const editComment = (index) => {
        setEditingIndex(index);
        setEditText(comments[index].text);
    };

    const saveEdit = (commId) => {
        const updatedComments = [...comments];

        putComment(commId, editText, id, token).then(
            response => {
                updatedComments[editingIndex].text = response.data.text;
                setComments(updatedComments);
                setEditingIndex(null);
            }
        ).catch(error => {
            console.error('Erreur lors de la modification du commentaire', error);
        })
    };

    const deleteComment = (index, commId) => {
        const updatedComments = comments.filter((_, i) => i !== index);

        deleteCommentAPI(commId, token).then(
            response => {
                if (response.status === 400) throw new Error('Erreur lors de la suppression du commentaire');
                setComments(updatedComments);
            }
        ).catch(error => {
            console.error('Erreur lors de la suppression du commentaire', error);
        })
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

                        {currentUser === null ? null :
                            <Box display="flex" alignItems="center">
                                <Typography>📝 Votre note :</Typography>
                                <Rating value={userRating} onChange={handleRate} />
                            </Box>
                        }

                        <Box display="flex" alignItems="center">
                            <Button variant="contained" color="primary" className="watch-button">
                                🎥 Regarder le film
                            </Button>
                            {currentUser === null ? null :
                                <Button variant="outlined" color="secondary" className="add-to-list">
                                    📌 Ajouter à ma liste
                                </Button>
                            }

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
                                    🧑 {comment.username} - {new Date(comment.date).toLocaleDateString()}
                                </Typography>

                                {comment.username === currentUser && (
                                    <Box>
                                        <Button size="small" color="primary" onClick={() => editComment(index)}>
                                            Modifier
                                        </Button>
                                        <Button size="small" color="secondary" onClick={() => deleteComment(index, comment.id)}>
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

                            {editingIndex === index && (
                                <Button size="small" color="success" onClick={() => saveEdit(comment.id)}>
                                    Enregistrer
                                </Button>
                            )}
                        </CardContent>
                    </Card>
                ))}

                {currentUser === null ? null :
                    <TextField
                        label="Ajouter un commentaire"
                        variant="outlined"
                        fullWidth
                        value={newComment}
                        onChange={(e) => setNewComment(e.target.value)}
                    />}
                {currentUser === null ? null :
                    <Button variant="contained" color="primary" onClick={addComment}>
                        Ajouter
                    </Button>
                }
            </div>
        </div>
    );
}

export default FilmPage;
