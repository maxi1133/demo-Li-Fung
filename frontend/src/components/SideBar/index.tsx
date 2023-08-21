import { FC, Fragment } from "react";
import "./SideBar.scss";

export const SideBar: FC<{ contact: Contact }> = ({ contact }) => {
  return contact ? (
    <div className="side-bar">
      <ul>
        <li>
          <div className="header">Contact ID</div>
          <div className="content">{contact.address}</div>
        </li>
        <li>
          <div className="header">Company</div>
          <div className="content">{contact.company}</div>
        </li>
        <li>
          <div className="header">City</div>
          <div className="content">{contact.city}</div>
        </li>
        <li>
          <div className="header">Description</div>
          <div className="content">{contact.description}</div>
        </li>
      </ul>
    </div>
  ) : (
    <Fragment></Fragment>
  );
};
