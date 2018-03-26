create table subscription (
  id uuid not null,
  customerId uuid not null,
  productId uuid not null,
  validFrom timestamp WITH TIMEZONE,
  validTo timestamp WITH TIMEZONE
);
