import { FC, useEffect, useRef, useState } from "react";
import { CONTACT_LIST } from "../../constants/Constants";
import "./DataTable.scss";
import infoIcon from "../../icons/icons8-info.svg";

export const DataTable: FC<{ searchValue: string; onClickIcon: Function }> = ({
  searchValue,
  onClickIcon,
}) => {
  let ref = useRef("");
  let [currentList, setCurrentList] = useState([]);
  let [selectedMap, setSelectedMap] = useState({});

  useEffect(() => {
    if (searchValue) {
      let newList = CONTACT_LIST.filter((item) =>
        item.contact_name.toLowerCase().includes(searchValue)
      );
      setCurrentList(newList);
    }
  }, [searchValue]);

  let onRowClick = (contact: Contact) => {
    onClickIcon(contact);
    ref.current = contact.contact_id;
  };

  let onCheck = (id: string, value: boolean) => {
    selectedMap[id] = value;
    setSelectedMap({ ...selectedMap });
    console.log(selectedMap);
  };

  return (
    <table className="custom-table">
      <thead>
        <tr>
          <th className="fit-value-area">No</th>
          <th className="fit-value-area">#</th>
          <th>contact name</th>
          <th>phone</th>
          <th className="fit-value-area"></th>
        </tr>
      </thead>
      <tbody>
        {(searchValue ? currentList : CONTACT_LIST).map(
          (contact: Contact, index: number) => {
            return (
              <tr
                key={contact.contact_id}
                className={ref.current === contact.contact_id ? "active" : ""}
                onClick={() => onRowClick(contact)}
              >
                <td className="icon-area">{index + 1}</td>
                <td className="check-box">
                  <input
                    type="checkbox"
                    onChange={(e) =>
                      onCheck(contact.contact_id, e.target.checked)
                    }
                    defaultChecked={selectedMap[contact.contact_id]}
                  />
                </td>
                <td>{contact.contact_name}</td>
                <td>{contact.phone}</td>
                <td className="icon-area">
                  <img
                    src={infoIcon}
                    height={20}
                    width={20}
                    onClick={() => onRowClick(contact)}
                    alt=""
                  />
                </td>
              </tr>
            );
          }
        )}
      </tbody>
    </table>
  );
};
