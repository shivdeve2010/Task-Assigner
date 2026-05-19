import React, { useState, useEffect } from 'react';
import axios from 'axios';

const AssignTask = () => {
  const [task, setTask] = useState({
    start_date: '',
    target_date: '',
    task_desc: '',
  });

  const [users, setUsers] = useState([]); // store user list
  const [userId, setUserId] = useState(''); // selected user ID

  // Fetch users when the component loads
  useEffect(() => {
    const fetchUsers = async () => {
      try {
        const response = await axios.get('http://localhost:8080/users');
        // console.log("API Response:", response.data);
        setUsers(response.data);
      } catch (error) {
        console.error('Error fetching users:', error);
      }
    };
    fetchUsers();
  }, []);

  const handleChange = (e) => {
    const { name, value } = e.target;
    setTask({ ...task, [name]: value });
  };

  const handleSubmit = async (e) => {
    e.preventDefault();

    // if (!userId) {
    //   alert('⚠️ Please select a user to assign the task to.');
    //   return;
    // }

    try {
      // Use the dynamic endpoint with userId
      await axios.post(`http://localhost:8080/user/${userId}/task`, task);
      alert('✅ Task assigned successfully!');
      setTask({ start_date: '', target_date: '', task_desc: '' }); // reset form
      setUserId(''); // reset selected user
    } catch (error) {
      console.error('Error assigning task:', error);
      alert('❌ Failed to assign task.');
    }
  };

  return (
    <div style={styles.container}>
      <h2>Assign New Task</h2>
      <form onSubmit={handleSubmit} style={styles.form}>
        
        {/* Select User Dropdown */}
        <label style={styles.label}>Assign To:</label>
        <select
          value={userId}
          onChange={(e) => setUserId(e.target.value)}
          required
          style={styles.input}
        >
          <option value="">-- Select User --</option>
          {Array.isArray(users) &&
  users.map((user) => (
    <option key={user.id} value={user.id}>
      {user.name} ({user.username})
    </option>
  ))}
        </select>

        <label style={styles.label}>Start Date:</label>
        <input
          type="date"
          name="start_date"
          value={task.start_date}
          onChange={handleChange}
          required
          style={styles.input}
        />

        <label style={styles.label}>Target Date:</label>
        <input
          type="date"
          name="target_date"
          value={task.target_date}
          onChange={handleChange}
          required
          style={styles.input}
        />

        <label style={styles.label}>Task Description:</label>
        <textarea
          name="task_desc"
          value={task.task_desc}
          onChange={handleChange}
          placeholder="Enter task details..."
          required
          style={styles.textarea}
        ></textarea>

        <button type="submit" style={styles.button}>Assign Task</button>
      </form>
    </div>
  );
};

// Basic inline styles
const styles = {
  container: {
    maxWidth: '420px',
    margin: '60px auto',
    padding: '20px 25px',
    borderRadius: '12px',
    backgroundColor: '#f8f9fa',
    boxShadow: '0px 4px 10px rgba(0,0,0,0.1)',
  },
  form: {
    display: 'flex',
    flexDirection: 'column',
  },
  label: {
    marginTop: '12px',
    marginBottom: '5px',
    fontWeight: 'bold',
    color: '#333',
  },
  input: {
    padding: '8px',
    borderRadius: '6px',
    border: '1px solid #ccc',
    fontSize: '14px',
  },
  textarea: {
    padding: '8px',
    borderRadius: '6px',
    border: '1px solid #ccc',
    fontSize: '14px',
    minHeight: '80px',
  },
  button: {
    marginTop: '18px',
    padding: '10px',
    backgroundColor: '#007bff',
    color: 'white',
    fontWeight: 'bold',
    border: 'none',
    borderRadius: '6px',
    cursor: 'pointer',
    transition: 'background 0.3s ease',
  },
};

export default AssignTask;
