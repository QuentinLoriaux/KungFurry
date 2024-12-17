import {useState} from "react";
import {Box, Button, FormControl, InputLabel, MenuItem, Select} from "@mui/material";

function EditUserForm({ user , onSubmit }) {
    const [roleId, setRoleId] = useState(user?.roleId || '');

    const handleEditUser = (e) => {
        e.preventDefault();

        const updatedUser = {
            username: user.username,
            md5Hex: user.md5Hex,
            roleId: roleId,
        };
        onSubmit(updatedUser);
    }

  return (
      <Box
          component="form"
          onSubmit={handleEditUser}
          sx={{
              display: 'flex',
              flexDirection: 'column',
              gap: 2,
              maxWidth: 400,
              margin: 'auto',
              padding: 2,
              border: '1px solid #ccc',
              borderRadius: 2,
              boxShadow: 3,
          }}
      >

          <FormControl variant="outlined" required fullWidth>
              <InputLabel id="role-select-label">Rôle</InputLabel>
              <Select
                  labelId="role-select-label"
                  value={roleId}
                  onChange={(e) => setRoleId(e.target.value)}
                  label="Rôle"
               >
                  <MenuItem value={2}>Administrateur</MenuItem>
                  <MenuItem value={1}>Utilisateur</MenuItem>
              </Select>
          </FormControl>
          <Button type="submit" variant="contained" color="primary">
              Modifier
          </Button>
      </Box>
  );
}

export default EditUserForm;