import React, { useState } from 'react';


const Profile = () => {
  const [isEditing, setEditing] = useState(false);

  const [lastName, setLastName] = useState('Doe');
  const [phoneNumber, setPhoneNumber] = useState('123-456-7890');
  const [email, setEmail] = useState('johndoe@example.com');
  const [address, setAddress] = useState('123 Main St, City');
  const [gender, setGender] = useState('Male');
  const [birthdate, setBirthdate] = useState('1990-01-01'); // Format as needed

  const [editedFields, setEditedFields] = useState({
    lastName: '',
    phoneNumber: '',
    email: '',
    address: '',
    gender: '',
    birthdate: '',
  });

  const handleEditClick = () => {
    setEditedFields({
      lastName,
      phoneNumber,
      email,
      address,
      gender,
      birthdate,
    });
    setEditing(true);
  };

  const handleSaveClick = () => {
    setEditing(false);
    // Perform save action (e.g., send data to the server)

    // Optionally, update state with the edited values
    setLastName(editedFields.lastName);
    setPhoneNumber(editedFields.phoneNumber);
    setEmail(editedFields.email);
    setAddress(editedFields.address);
    setGender(editedFields.gender);
    setBirthdate(editedFields.birthdate);
  };

  const handleInputChange = (field, value) => {
    setEditedFields({
      ...editedFields,
      [field]: value,
    });
  };

  return (
    <div>
      <h2>Hồ Sơ Cá Nhân</h2>
      <div>
        <label>Họ và Tên:</label>
        {isEditing ? (
          <input
            type="text"
            value={editedFields.lastName}
            onChange={(e) => handleInputChange('lastName', e.target.value)}
          />
        ) : (
          <span>{lastName}</span>
        )}
      </div>
      <div>
        <label>Số Điện Thoại:</label>
        {isEditing ? (
          <input
            type="text"
            value={editedFields.phoneNumber}
            onChange={(e) => handleInputChange('phoneNumber', e.target.value)}
          />
        ) : (
          <span>{phoneNumber}</span>
        )}
      </div>
      {/* Các trường thông tin khác ở đây */}
      <div>
        {isEditing ? (
          <button onClick={handleSaveClick}>Lưu</button>
        ) : (
          <button onClick={handleEditClick}>Chỉnh Sửa</button>
        )}
      </div>
    </div>
  );
};

export default Profile;