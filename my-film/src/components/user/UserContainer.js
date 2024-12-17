import {useEffect, useState} from "react";
import {getAllUsers} from "../../api/UserApi";
import UserList from "./UserList";

const UserContainer = () => {
    const [users, setUsers] = useState([])

    useEffect(() => {
        getAllUsers().then(response => {
            setUsers(response.data)
        }).catch(err => {
            console.log(err);
        })
    }, []);

    return (
        <div style={{flex: 1, marginLeft: "20px", display: "flex", justifyContent: "center"}}>
            <UserList users={users} setUsers={setUsers}/>
        </div>
    );
}

export default UserContainer