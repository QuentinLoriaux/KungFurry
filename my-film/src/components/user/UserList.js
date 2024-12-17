import UserCard from "./UserCard";

export default function UserList(props) {
    if (!props.users || props.users.length === 0) {
        return <p>Aucun utilisateur à afficher.</p>;
    }

    return (
        <div style={{display: "flex", flexWrap: "wrap", gap: "20px"}}>
            {props.users.map(user => (
                <UserCard key={user.id} user={user} setUsers={props.setUsers}/>
            ))}
        </div>
    )
}