import CreateFilmForm from "./CreateFilmForm";
import FilmList from "./FilmList";
import React, {useState, useEffect, useCallback} from "react";
import { getAllFilms, postFilm } from "../../api/FilmApi";
import IconButton from '@mui/material/IconButton';
import { Button, FormControl, InputLabel, MenuItem, Select, TextField } from "@mui/material";
import { SwapVert, Visibility, VisibilityOff } from "@mui/icons-material";
import Dialog from "@mui/material/Dialog";
import DialogContent from "@mui/material/DialogContent";
import PageSelector from "../PageSelector";

const FilmContainer = () => {
    const [currentPage, setCurrentPage] = useState(1);
    const [totalPages, setTotalPages] = useState(1);
    const pageSize = 2;

    const [open, setOpen] = useState(false);
    const [Films, setFilms] = useState([]);
    const [searchQuery, setSearchQuery] = useState("");
    const [sortOption, setSortOption] = useState("");
    const [isDescending, setIsDescending] = useState(false);
    const [onlySeen, setOnlySeen] = useState(false);

    const handleOpen = () => setOpen(true);
    const handleClose = () => setOpen(false);

    const fetchFilms = useCallback(() => {
        getAllFilms(
            currentPage,
            pageSize,
            searchQuery.toLowerCase(),
            sortOption,
            isDescending ? "desc" : "asc"
        )
            .then((response) => {
                setFilms(response.data.data);
                setTotalPages(Math.ceil(response.data.total / pageSize));
            })
            .catch((err) => {
                console.error("Erreur lors de la récupération des films :", err);
            });
    }, [currentPage, pageSize, searchQuery, sortOption, isDescending]);

    useEffect(() => {
        fetchFilms();
    }, [fetchFilms]);

    const handlePageChange = (page) => {
        setCurrentPage(page);
    };

    const handleCreateFilm = (newFilm) => {
        postFilm(newFilm)
            .then(() => {
                fetchFilms();
            })
            .catch((error) => {
                console.error('Erreur lors de la création du film:', error);
                alert('Une erreur est survenue lors de l’ajout du film.');
            });
        handleClose();
    };

    const handleSearch = (query) => {
        setSearchQuery(query);
    };

    const handleSort = (option) => {
        setSortOption(option);
    };

    const toggleSortOrder = () => {
        setIsDescending(!isDescending);
    };

    const toggleSeen = () => {
        setOnlySeen(!onlySeen);
    }

    return (
        <div>
            <div
                style={{
                    display: 'flex',
                    alignItems: 'center',
                    gap: '1rem',
                    background: "lightgray",
                    width: "100vw",
                    justifyContent: "center"
                }}
            >
                <div style={{ margin: "10px" }}>
                    <TextField
                        placeholder="Rechercher..."
                        variant="outlined"
                        size="small"
                        style={{
                            flexGrow: 1,
                            maxWidth: 300,
                            backgroundColor: "white",
                            borderRadius: "5px",
                        }}
                        onChange={(e) => handleSearch(e.target.value)}
                    />
                </div>
                <div style={{ margin: "10px", display: "flex" }}>
                    <IconButton onClick={toggleSeen}>
                        {onlySeen ? <VisibilityOff /> : <Visibility />}
                    </IconButton>
                    <InputLabel style={{ margin: "8px" }}>Trier par :</InputLabel>
                    <FormControl variant="outlined" size="small" style={{ minWidth: 150 }}>
                        <InputLabel id="dropdown-label">Choisir</InputLabel>
                        <Select
                            labelId="dropdown-label"
                            label="Choisir"
                            onChange={(e) => handleSort(e.target.value)}
                            value={sortOption}
                        >
                            <MenuItem value="titre">Titre</MenuItem>
                            <MenuItem value="duree">Durée</MenuItem>
                        </Select>
                    </FormControl>
                    <IconButton onClick={toggleSortOrder}>
                        <SwapVert />
                    </IconButton>
                </div>

                <div style={{ margin: "10px" }}>
                    <Button
                        variant="contained"
                        color="primary"
                        size="small"
                        style={{ whiteSpace: 'nowrap' }}
                        onClick={handleOpen}
                    >
                        Ajouter
                    </Button>
                    <Dialog open={open} onClose={handleClose}>
                        <DialogContent>
                            <CreateFilmForm film={null} onSubmit={handleCreateFilm} />
                        </DialogContent>
                    </Dialog>
                </div>
            </div>

            <div style={{ flex: 1, marginLeft: "20px", display: "flex", justifyContent: "center" }}>
                <FilmList
                    films={Films}
                    setFilms={setFilms}
                    applyFilters={(newFilms) =>
                        fetchFilms()
                    }
                />
            </div>

            {/* Pagination */}
            <PageSelector
                totalPages={totalPages}
                currentPage={currentPage}
                onPageChange={handlePageChange}
            />
        </div>
    );
};

export default FilmContainer;
