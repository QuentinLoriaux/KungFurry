import React, {useState} from "react";
import {deleteUser, updateUser} from "../../api/UserApi";
import CardContent from "@mui/material/CardContent";
import Typography from "@mui/material/Typography";
import IconButton from "@mui/material/IconButton";
import DeleteIcon from "@mui/icons-material/Delete";
import Dialog from "@mui/material/Dialog";
import DialogTitle from "@mui/material/DialogTitle";
import DialogContent from "@mui/material/DialogContent";
import EditIcon from "@mui/icons-material/Edit";
import Card from "@mui/material/Card";
import EditUserForm from "./EditUserForm";


export default function UserCard(props) {
    const [open, setOpen] = useState(false);

    const handleClickOnEditButton = () => {
        setOpen(true);
    };

    const handleClose = () => {
        setOpen(false);
    };


    const handleEditSubmit = (updatedUser) => {
        updateUser(props.user.id , updatedUser).then(response => {
            props.setUsers((prevUsers) => prevUsers.map((user) => {
                if (user.id === response.data.id) {
                    return response.data;
                }
                return user;
            }));
            handleClose();
        }).catch(error => {
            console.error('Error editing user:', error);
            alert('An error occurred while editing the user.');
            handleClose();
        });
    };

    const handleClickOnDeleteButton = () => {
        const id = props.user.id;
        deleteUser(id)
            .then(() => {
                props.setUsers((prevUsers) => prevUsers.filter((user) => user.id !== id));
            })
            .catch((error) => {
                console.error('Erreur lors de la suppression de l\'utilisateur :', error);
                alert('Une erreur est survenue lors de la suppression de l\'utilisateur :');
            });
    };

    return (
        <Card variant="outlined" sx={{ margin: 2, maxWidth: 300 }}>
            <CardContent>
                <Typography variant="h5" gutterBottom>
                    {props.user.username}
                </Typography>
                <Typography variant="body1">
                    Rôle : {props.user.roleId === 2 ? "Administrateur" : "Utilisateur"}
                </Typography>
            </CardContent>

            <IconButton onClick={handleClickOnDeleteButton}>
                <DeleteIcon/>
            </IconButton>
            <IconButton onClick={handleClickOnEditButton}>
                <Dialog onClose={handleClose} open={open}>
                    <DialogTitle>Modifier un utilisateur</DialogTitle>
                    <DialogContent>
                        <EditUserForm user={props.user} onSubmit={handleEditSubmit}/>
                    </DialogContent>
                </Dialog>
                <EditIcon/>
            </IconButton>
        </Card>
    )
}