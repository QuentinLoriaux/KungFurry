import {useEffect, useState} from "react";
import {getAllUsers} from "../../api/UserApi";
import UserList from "./UserList";

const UserContainer = (token) => {
    const [users, setUsers] = useState([])
    useEffect(() => {
        getAllUsers(token).then(response => {
            setUsers(response.data)
        }).catch(err => {
            console.log(err);
        })
    }, []);

    return (
        <div style={{flex: 1, marginLeft: "20px", display: "flex", justifyContent: "center"}}>
            <UserList users={users} setUsers={setUsers} token={token}/>
        </div>
    );
}

export default UserContainer