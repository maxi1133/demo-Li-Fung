import { useEffect, useState } from "react";
import { DataTable } from "./components/DataTable";
import { SideBar } from "./components/SideBar";

const App = () => {
  let [searchValue, setSearchValue] = useState("");
  let [clickedContact, setClickedContact] = useState(null as Contact);

  useEffect(() => {
    return () => { };
  }, []);

  let onClickIcon = (contact: Contact) => setClickedContact(contact);

  return (
    <div>
      <h3>Contact list</h3>

      <input
        type="text"
        placeholder="input"
        maxLength={50}
        value={searchValue}
        onChange={(e) => setSearchValue(e.target.value)}
      />

      <DataTable searchValue={searchValue} onClickIcon={onClickIcon} />

      <SideBar contact={clickedContact} />
    </div>
  );
};

export default App;
