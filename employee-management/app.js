document.addEventListener('DOMContentLoaded', function () {
    const employeeTableBody = document.getElementById('employeeTableBody');

    fetch('http://localhost:8080/employees')
        .then(response => response.json())
        .then(data => {
            data.forEach(employee => {
                const row = document.createElement('tr');
                row.innerHTML = `
                    <td>${employee.id}</td>
                    <td>${employee.name}</td>
                    <td>${employee.wage}</td>
                `;
                employeeTableBody.appendChild(row);
            });
        })
        .catch(error => console.error('There was an error fetching the employees!', error));

        const employeeForm = document.getElementById('employeeForm');

        employeeForm.addEventListener('submit', function (event) {
            event.preventDefault();
    
            const formData = new FormData(employeeForm);
            const employeeData = {
                name: formData.get('name'),
                wage: formData.get('wage')
            };
    
            fetch('http://localhost:8080/employees', {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify(employeeData)
            })
            .then(response => {
                if (!response.ok) {
                    throw new Error('Failed to create employee');
                }
                return response.json();
            })
            .then(data => {
                alert('Employee created successfully');
                employeeForm.reset(); // Reset form fields after successful submission
            })
            .catch(error => {
                console.error('Error creating employee:', error);
                alert('Failed to create employee. Please try again.');
            });
        });
});
