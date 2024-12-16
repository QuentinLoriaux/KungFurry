import React from 'react';
import Pagination from '@mui/material/Pagination';
import Stack from '@mui/material/Stack';

const PageSelector = ({ totalPages, currentPage, onPageChange }) => {
    const handleChange = (event, value) => {
        onPageChange(value);
    };

    return (
        <Stack spacing={2} alignItems="center" style={{ marginTop: '20px' }}>
            <Pagination
                count={totalPages}
                page={currentPage}
                onChange={handleChange}
                color="primary"
                size="large"
            />
        </Stack>
    );
};

export default PageSelector;